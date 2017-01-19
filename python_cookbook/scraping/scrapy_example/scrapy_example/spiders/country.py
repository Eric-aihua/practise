# -*- coding: utf-8 -*-
import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule


class CountrySpider(CrawlSpider):
    name = 'country'
    #allowed_domains = ['localhost:8000/places/default']
    start_urls = ['http://localhost:8000/places/default/']

    rules = (
        Rule(LinkExtractor(allow='/index/'), follow=True),
        Rule(LinkExtractor(allow='/view/',deny='/user/'), callback='parse_item', follow=True),
    )

    def parse_item(self, response):
        i = {}
        #print '#:%s' %(response)
	country=response.css('#places_country__row > td.w2p_fw::text').extract()
	population=response.css('#places_population__row > td.w2p_fw::text').extract()
	print country,population
	i['country']=country
	i['population']=population
        #i['domain_id'] = response.xpath('//input[@id="sid"]/@value').extract()
        #i['name'] = response.xpath('//div[@id="name"]').extract()
        #i['description'] = response.xpath('//div[@id="description"]').extract()
        return i
