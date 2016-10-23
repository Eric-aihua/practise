# encoding:utf-8

from basic.crawling_utils import *
from basic.scrape_callbacks import *

def scrape(url,regex,cache):
    link_download(url,regex,max_depth=5,scrape_call_back=CSVScrapeCallBack(),cache=cache)

if __name__ == '__main__':
    start_url='http://search.51job.com/jobsearch/search_result.php?fromJs=1&jobarea=180200%2C00&district=000000&funtype=0000&industrytype=00&issuedate=9&providesalary=99&keyword=hadoop&keywordtype=2&curr_page=1&lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&list_type=0&fromType=14&dibiaoid=0&confirmdate=9'
    regex='/(index|view)/'


