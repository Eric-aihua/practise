from hive import hive_client

__author__ = 'eric.sun'

def clean_overdue_partitions():
    tables = ['atsani_pro_log',
                  'bailu_pro_log',
                  'choi_wan_pro_log',
                  'coll_zmq_log',
                  'dbtest',
                  'device_card',
                  'device_card_tt_k',
                  'device_disk',
                  'device_hard',
                  'device_hash_ip',
                  'device_process',
                  'device_status',
                  'etau_pro_log',
                  'hagibis_pro_log',
                  'hato_pro_log',
                  'higos_pro_log',
                  'host_daily_risk',
                  'host_vul_count',
                  'nf_dev_version_status',
                  'omais_pro_log',
                  'process_monitor',
                  'rcm_dev_status',
                  'rsas_pro_log',
                  'runtime_log',
                  'scan_infos',
                  'scan_vuls',
                  'streaming_status',
                    'task_ip',
                'task_statistics',
                  'waflog_access',
                  'word_count',
                  'word_count2',
                  'word_count3']
    hive_client.clean_table_partitions(tables,'2016042800')


clean_overdue_partitions()