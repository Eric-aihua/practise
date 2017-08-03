# -*- coding:utf-8 -**
import unittest
from influxdb import InfluxDBClient
from unittest import TestCase

__author__ = 'sunaihua'

# SQL = "SELECT sum(avg_dr_bps) as dr_bps, sum(max_dr_bps) as max_dr_bps,sum(avg_in_bps) as in_bps,sum(max_in_bps) as max_in_bps,sum(avg_pa_bps) as pa_bps FROM one_year.collapsar_flow_5m WHERE ( org='zWJyGF' or org='97eXGR' or org='aaddccd' ) AND partner='b97eecc3-81f2-4552-a483-43dbc657d3d4' AND  time >= 1500519959s and time <= 1501124759s group by time(5m) fill(0)"
SQL = "SELECT sum(filter_bps) as filter_bps FROM attack_event WHERE pg='2ecc5d68-4f3a-4a04-9c75-18241b477397' AND  time >= 1501656211s and time <= 1501667011s group by time(30s),attack_type fill(0)"

host = '10.5.25.18'
dbname = 'cloudportal'
client = InfluxDBClient(host=host, database=dbname)


class TestInfluxDBQuery(TestCase):
    def testSingleSeriesQuery(self):
        result = client.query(SQL, epoch='s')
        # client.request()
        print list(result.keys())
        print list(result.items())
        print list(result.get_points(tags={'attack_type': '0'}))
        print list(result.get_points(tags={'attack_type': '1'}))
        print list(result.get_points(tags={'attack_type': '2'}))


if __name__ == '__main__':
    unittest.main()
