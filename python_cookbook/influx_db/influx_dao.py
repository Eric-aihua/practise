# -*- coding:utf-8 -**
__author__ = 'sunaihua'
import pandas as pd
from influxdb import InfluxDBClient
import time
import json

test_file = 'C:\\Users\\Eric\\Desktop\\show.archive_2016080311_0000_01'


def read_file():
    lines = (x for x in open(test_file))
    return lines


def build_influx_record(line):
    # records = []
    # bbs,pps
    # incoming,droped,passed
    # record_template = 'collapsar_flow,dip=%s,pg=%s,deviceip=%s,partner=%s,type=%s,unit=%s value=%s'
    record_template = 'collapsar_flow,dip=%s,pg=%s,deviceip=%s,partner=%s in_pps=%s,pa_pps=%s,dr_pps=%s,in_bps=%s,pa_bps=%s,dr_bps=%s'
    dip = line[4]
    pg = 'testpg'
    device_ip = line[0]
    partner = 'testpar'
    in_bps = line[6]
    in_pps = line[7]
    out_bps = line[16]
    out_pps = line[17]

    data_json = {
        "measurement": "collapsar_flow",
        "tags": {
            "dip": dip,
            "pg": pg,
            "deviceip": device_ip,
            "partner": partner,
        },
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
    return [data_json]


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


if __name__ == '__main__':
    host = '10.5.25.18'
    port = 8086
    dbname = 'cloudportal'
    # Temporarily used to avoid line protocol time conversion issues #412, #426, #431.

    client = InfluxDBClient(host=host, database=dbname)
    # test(client)

    lines = read_file()

    while True:
        for line in lines:
            record_count = 0
            org_data = line.split('\t')
            record = build_influx_record(org_data)
            time.sleep(0.2)
            client.write_points(record, protocol='json')
            print 'Insert Successful:%s' % record
