# -*- coding:utf-8 -**
import unittest
from influxdb import InfluxDBClient
from unittest import TestCase

__author__ = 'sunaihua'

# SQL = "SELECT sum(avg_dr_bps) as dr_bps, sum(max_dr_bps) as max_dr_bps,sum(avg_in_bps) as in_bps,sum(max_in_bps) as max_in_bps,sum(avg_pa_bps) as pa_bps FROM one_year.collapsar_flow_5m WHERE ( org='zWJyGF' or org='97eXGR' or org='aaddccd' ) AND partner='b97eecc3-81f2-4552-a483-43dbc657d3d4' AND  time >= 1500519959s and time <= 1501124759s group by time(5m) fill(0)"
# SQL = "SELECT sum(filter_bps) as filter_bps FROM attack_event WHERE pg='1ecc5d68-4f3a-4a04-9c75-18241b477397' AND  time >= 1501656211s and time <= 1501667011s group by time(30s),attack_type fill(0)"
# SQL = """select top(max_traffic_in_bps,sip,10) as max_traffic_in_bps,max_traffic_in_pps,traffic_in_bits,traffic_in_packages,traffic_drop_bits,traffic_drop_packages from
#                     (select  max(in_bps) as max_traffic_in_bps,max(in_pps) as max_traffic_in_pps,sum(in_pps)*30 as traffic_in_packages,
#                         sum(in_bps)*30 as traffic_in_bits,sum(dr_bps)*30 as traffic_drop_bits, sum(dr_pps)*30 as traffic_drop_packages  from six_months.stat_sip
#                         where time >= 1504681200s and time <= 1504689200s  and   org='QiRFUD'  group by sip)"""
SQL = 'select sum(filter_bps)*30 as filter_bits,sum(filter_pps)*30 as filter_packets,max(filter_bps) as filter_peak_bps, max(filter_pps) as filter_peak_pps from attack_event where time > 1505175533s and time < 1505186333s group by attack_type'

host = '10.5.25.201'
dbname = 'cloudportal'
client = InfluxDBClient(host=host, database=dbname)
"""
    {
        "xAxisData": [timestamp / string, timestamp / string...],
        "UPDFlood": {"yAxisData": [value, value...]}
        "HttPFlood": {"yAxisData": [value, value...]}
        "Others": {"yAxisData": [value, value...]}
    },

"""
ATTACK_TYPE_MAP = {
    '1': 'Syn Flood',
    '2': 'Ack Flood',
    '3': 'UDP Flood',
    '4': 'ICMP Flood',
    '5': 'Conn Flood',
    '6': 'Stream Flood',
    '7': 'Land Flood',
    '8': 'Cont Flood',
    '9': 'CC Flood',
    '10': 'UDP-DNS Flood',
    '0': 'Other',
}


def convert_result_format(result):
    final_result = {'xAxisData': []}
    if result:
        # keys结果数据：[(u'attack_event', {u'attack_type': u'0'}), (u'attack_event', {u'attack_type': u'1'})]
        attack_types = map(lambda attack_event: attack_event[1]['attack_type'], result.keys())
        # get_points结果数据:[{u'filter_bps': 0, u'time': 1501656210}, {u'filter_bps': 0, u'time': 1501656240}, ...]
        time_points = map(lambda point: point.values()[1], result.get_points(tags={'attack_type': attack_types[0]}))
        final_result['xAxisData'] = time_points
        for attack_type in attack_types:
            value_points = map(lambda point: point.values()[0], result.get_points(tags={'attack_type': attack_type}))
            yAxisData = {'yAxisData': value_points}
            final_result[ATTACK_TYPE_MAP[attack_type]] = yAxisData

    return final_result


class TestInfluxDBQuery(TestCase):
    def testSingleSeriesQuery(self):
        result = client.query(SQL, epoch='s')
        # client.request()
        # print list(result.keys())
        print list(result.items())
        # print list(result.get_points(tags={'attack_type': '0'}))
        # print list(result.get_points())
        # print list(result.get_points(tags={'attack_type': '1'}))
        # print convert_result_format(result)

    def testSummryQuery(self):
        sql = "select sum(filter_bps)*30 as nomal_filter_bps from attack_event where pg='2ecc5d68-4f3a-4a04-9c75-18241b477397' AND dip='61.4.185.48' AND   begin_time > 1470193130 and begin_time < 1470193260"
        result = client.query(sql, epoch='s')
        print list(result.get_points())[0].get("nomal_filter_bps", 0)
        print list(result.keys())
        print list(result.items())

    def testTrafficQuery(self):
        sql = "SELECT sum(dr_bps) as dr_bps, sum(dr_bps) as max_dr_bps,sum(in_bps) as in_bps,sum(in_bps) as max_in_bps,sum(pa_bps) as pa_bps FROM six_months.collapsar_flow WHERE pg='916391cd-c3bd-4918-82b9-5343be086c16' AND  time >= 1503292028s and time <= 1503295628s group by time(30s) fill(0)"
        result = client.query(sql, epoch='s')
        print result.get_points()
        print list(result.get_points())

    def testReportTopSourceIP(self):
        sql = "select  max(max_in_bps) as max_traffic_in_bps,max(max_in_pps) as max_traffic_in_pps,sum(avg_in_pps)*3600 as traffic_in_packages,sum(avg_in_bps)*3600 as traffic_in_bits,sum(avg_dr_bps)*3600 as traffic_drop_bits, sum(avg_dr_pps)*3600 as traffic_drop_packages from one_year.stat_sip_5m where time >= 1505923199s and time <= 1506053149s  and  ( dip='116.0.28.38')  group by sip"
        result = client.query(sql, epoch='s')
        print list(result.keys())
        # print list(result.get_points())
        all_result = []
        sips = map(lambda attack_type: attack_type[1]['sip'], result.keys())
        for key in sips:
            sip_point = list(result.get_points(tags={'sip': key}))[0]
            sip_point.update(sip=key)
            # print sip_point
            all_result.append(sip_point)

        sort_data = sorted(all_result, lambda x, y: cmp(x['max_traffic_in_bps'], y['max_traffic_in_bps']), reverse=True)
        print sort_data

    def testAttackTypeDistribution(self):
        # sql = 'select sum(filter_bps)*30 as filter_bits,sum(filter_pps)*30 as filter_packets,max(filter_bps) as filter_peak_bps, max(filter_pps) as filter_peak_pps from attack_event where time > 1505175533s and time < 1505186333s group by attack_type'
        sql2 = 'select count(distinct(begin_time)) from attack_event where time >= 1505750400s and time <= 1505923199s  and   org=\'nBwc2A\' group by attackkey'
        # table_result = client.query(sql, epoch='s')
        char_result = client.query(sql2, epoch='s')

        # attack_types = map(lambda attack_type: attack_type[1]['attack_type'], char_result.keys())
        attack_type_counts = self.convert_char_result(char_result)
        # # 把按照attack_key的统计结果，转换成Attack_type的统计
        # attack_type_counts = [(attack_key_count[0].split('_')[2], attack_key_count[1]) for attack_key_count in
        #                      attack_key_counts]
        # from collections import defaultdict
        # attack_type_dist = defaultdict(list)
        # for k, v in attack_type_counts:
        #     attack_type_dist[k] = attack_type_dist.get(k, 0) + v
        # # 对每个attack_type的进行统计
        # attack_type_dist = [{k: v} for k, v in attack_type_dist.items()]

        # attack_types = map(lambda attack_event: attack_event[1]['attack_type'], table_result.keys())
        # table_data = []
        # for attack_type in attack_types:
        #     points = list(table_result.get_points(tags={'attack_type': attack_type}))[0]
        #     table_data.append(
        #         [attack_type, dict(filter_bits=points['filter_bits'], filter_packets=points['filter_packets']),
        #          dict(filter_peak_bps=points['filter_peak_bps'], filter_peak_pps=points['filter_peak_pps'])])
        # print dict(char=attack_type_counts, table=table_data)

    def convert_char_result(self, char_result):
        attack_keys = map(lambda attack_type: attack_type[1]['attackkey'], char_result.keys())
        attack_type_counts = {}
        for attack_key in attack_keys:
            attack_type=attack_key.split('_')[2]
            attack_type_counts[attack_type]=attack_type_counts.get(attack_type)+1 if attack_type_counts.get(attack_type,0) else 1

        attack_type_dist = [{ATTACK_TYPE_MAP[element[0]]: element[1]} for element in attack_type_counts.items()]

        print("attack type char:%s" % attack_type_dist)
        return attack_type_dist


if __name__ == '__main__':
    unittest.main()
