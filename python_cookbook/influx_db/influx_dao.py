# -*- coding:utf-8 -**
import multiprocessing

__author__ = 'sunaihua'
from influxdb import InfluxDBClient
import time

traffic_test_file = 'C:\\Users\\Eric\\Desktop\\show.archive_2016080311_0000_01'
attack_event_test_file = 'C:\\Users\\Eric\\Desktop\\show.archive_2016080311_0000_00'
batch_size = 10
sleep_seconds=5


def read_file(f):
    lines = (x for x in open(f))
    return lines


def build_traffic_record(line, current_time):
    dip = line[4]
    pg = '2ecc5d68-4f3a-4a04-9c75-18241b477397'
    device_ip = line[0]
    partner = 'b97eecc3-81f2-4552-a483-43dbc657d3d4'
    org = 'aaddccd'
    in_bps = line[6]
    in_pps = line[7]
    out_bps = line[16]
    out_pps = line[17]

    data_json_traffic = {
        "measurement": "collapsar_flow",
        "tags": {
            "dip": dip,
            "pg": pg,
            "org": org,
            # "device_hash": device_ip,
            "partner": partner,
        },
        "time": current_time,
        "fields": {
            "in_pps": int(in_pps),
            "pa_pps": int(out_pps),
            "dr_pps": int(in_pps) - int(out_pps),
            "in_bps": int(in_bps),
            "pa_bps": int(out_bps),
            "dr_bps": int(in_bps) - int(out_bps),
        }
    }
    data_json_sip = {
        "measurement": "stat_sip",
        "tags": {
            "dip": dip,
            "sip": dip,
            "pg": pg,
            "org": org,
            # "device_hash": device_ip,
            "partner": partner,
        },
        "time": current_time,
        "fields": {
            "in_pps": int(in_pps),
            "pa_pps": int(out_pps),
            "dr_pps": int(in_pps) - int(out_pps),
            "in_bps": int(in_bps),
            "pa_bps": int(out_bps),
            "dr_bps": int(in_bps) - int(out_bps),
        }
    }
    # return record_template % (dip, pg, device_ip, partner, in_pps, out_pps, int(in_pps) - int(out_pps), in_bps, out_bps,int(in_bps) - int(out_bps))
    return [data_json_traffic, data_json_sip]


def build_attack_record(line, current_time):
    # device_hash = line[1]
    dip = line[4]
    pg = '2ecc5d68-4f3a-4a04-9c75-18241b477397'
    partner = 'b97eecc3-81f2-4552-a483-43dbc657d3d4'
    org = 'aaddccd'
    attack_type = line[5]
    attack_port = line[6]
    begin_time = line[8]
    end_time = line[9]
    filter_bytes = line[12]
    filter_packages = line[13]


    data_json_attackevent = {
        "measurement": "attack_event",
        "tags": {
            "dip": dip,
            "pg": pg,
            "org": org,
            # "device_hash": device_hash,
            "partner": partner,
            "attack_type": attack_type,
        },
        "time": current_time,
        "fields": {
            "begin_time": int(begin_time),
            "end_time": int(end_time),
            "filter_bps": int(filter_bytes),
            "filter_pps": int(filter_packages),
            "attack_port": attack_port
        }
    }
    print data_json_attackevent
    return [data_json_attackevent]


def test(client):
    json_body = [
        {
            "measurement": "cpu_load_short",
            "tags": {
                "host": "server08",
                "region": "us-west"
            },
            "fields": {
                "Float_value": 0.64
            }
        },
        {
            "measurement": "cpu_load_short",
            "tags": {
                "host": "server09",
                "region": "us-west"
            },
            "fields": {
                "Float_value": 0.68
            }
        },
        {
            "measurement": "cpu_load_short",
            "tags": {
                "host": "server10",
                "region": "us-west"
            },
            "fields": {
                "Float_value": 0.67
            }
        }
    ]
    test_query = 'select value from cpu_load_short;'
    client.write_points(json_body)
    result = client.query(test_query)
    print("Result: {0}".format(result))


def write_traffic(client):
    traffic_lines = read_file(traffic_test_file)
    while True:
        points = []
        for index, line in enumerate(traffic_lines):
            if index % batch_size == 0:
                current_time = int(time.time())
            org_data = line.split('\t')
            traffic_record = build_traffic_record(org_data, current_time)
            if index % batch_size == 0:
                points.extend(traffic_record)
                client.write_points(points, protocol='json', retention_policy='six_months', time_precision='s')
                points = []
                if index > 0:
                    time.sleep(sleep_seconds)
                print '%s Insert Traffic Successful' % time.ctime()
            else:
                points.extend(traffic_record)


def write_attackevent(client):
    attack_event_lines = read_file(attack_event_test_file)
    while True:
        points = []
        for index, line in enumerate(attack_event_lines):
            if index % batch_size == 0:
                current_time = int(time.time())
            org_data = line.split('\t')
            attackevent_record = build_attack_record(org_data, current_time)
            if index % batch_size == 0:
                points.extend(attackevent_record)
                client.write_points(points, protocol='json', time_precision='s')
                points = []
                if index > 0:
                    time.sleep(sleep_seconds)
                print '%s Insert AttackEvent Successful' % time.ctime()
            else:
                points.extend(attackevent_record)

if __name__ == '__main__':
    # host = '10.67.13.238'
    # host = '127.0.0.1'
    host = '10.5.31.24'
    port = 8086
    dbname = 'cloudportal'
    # Temporarily used to avoid line protocol time conversion issues #412, #426, #431.

    client = InfluxDBClient(host=host, database=dbname)
    traffc_process = multiprocessing.Process(target=write_traffic, args=(client,))
    attackevent_process = multiprocessing.Process(target=write_attackevent, args=(client,))
    traffc_process.start()
    attackevent_process.start()
