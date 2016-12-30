#encoding=utf-8
#!/usr/bin/env python
"""
this file used to control monitor scripts
"""


__author__ = 'sunaihua'

import logging
import sys
import os.path
import xml.dom.minidom

import xml


LOG = logging.getLogger('cloudagent')

COTTIG_FILE="plugin_coTTig.xml";

class Plugin:
    '''���������ض���Plugin'''
    def __init__(self,name,file,properties=None,description=None):
        self.name=name
        self.file=file
        self.description=description
        self.properties=properties
    def send_data(self):
        LOG.debug("Plugin:"+self.name+" sending data")

    def start(self):
        LOG.debug("Plugin:"+self.name+" begining")
        if os.path.isfile(self.file) and os.access(self.file, os.X_OK):
            exec self.file
        else:
            LOG.debug("Plugin:"+self.name+" exec file not found or haven't permission!")


    def stop(self):
        LOG.debug("Plugin:"+self.name+" stoping")

    def restart(self):
        LOG.debug("Plugin:"+self.name+" restarting")


class Plugins:
    '''�������������ͬ���õ�Plugin����'''
    def __init__(self,name,dir,interval,all_plugin,disable=None,):
        self.name=name;
        self.dir=dir;
        self.interval=interval;
        self.disable=disable;
        self.all_plugin=all_plugin

    def start(self):
        disable_plugin_list=self.disable.split(',')
        enable_plugin_list=[]
        for plugin in self.all_plugin:
            if plugin.name not in disable_plugin_list:
                enable_plugin_list.append(plugin)

    def stop(self):
        for plugin in self.all_plugin:
            plugin.stop()

    def restart(self):
        for plugin in self.all_plugin:
            plugin.restart()

class Collectors:
    def __init__(self,all_plugins=None):
        self.all_plugins=all_plugins

    def start(self):
        for plugins in self.all_plugins:
            plugins.start()


    def stop(self):
        for plugins in self.all_plugins:
            plugins.stop()

    def restart(self):
        for plugins in self.all_plugins:
            plugins.restart()


def read_coTTig():
    # ʹ��minidom�������� XML �ĵ�
    DOMTree = xml.dom.minidom.parse(COTTIG_FILE)
    collectors = DOMTree.documentElement
    # �ڼ����л�ȡ���е�plugins����
    plugins_elements = collectors.getElementsByTagName("plugins")

    plugins_list=[]

    #����Plugins��ϸ��Ϣ
    for plugins_element in plugins_elements:
        plugins_name=read_xml_leaf_value(plugins_element,'name')
        plugins_dir=read_xml_leaf_value(plugins_element,'dir')
        plugins_disable=read_xml_leaf_value(plugins_element,'disable')
        plugins_interval=read_xml_leaf_value(plugins_element,'interval')

        plugin_list=[]

        LOG.debug("#"*10+"Loading plugins��"+plugins_name)
        LOG.debug("Loading dir properties��"+plugins_dir)
        LOG.debug("Loading interval properties��"+plugins_interval)
        LOG.debug("Loading disable properties��"+plugins_disable)
        plugin_elements=plugins_element.getElementsByTagName("plugin")
        for plugin_element in plugin_elements:
            # ����Plugin��ϸ��Ϣ
            plugin_name=read_xml_leaf_value(plugin_element,'name')
            plugin_file=read_xml_leaf_value(plugin_element,'file')
            plugin_description=read_xml_leaf_value(plugin_element,'description')

            LOG.debug(" "*5+"Loading plugin��"+plugin_name)
            LOG.debug(" "*5+"Loading file properties��"+plugin_file)
            LOG.debug(" "*5+"Loading description properties��"+plugin_description)
            properties={}
            if len(plugin_element.getElementsByTagName("properties"))>0:
                properties_element=plugin_element.getElementsByTagName("properties")[0]
                property_elements=properties_element.getElementsByTagName("property")
                for property_element in property_elements:
                    name=read_xml_leaf_value(property_element,"name")
                    value=read_xml_leaf_value(property_element,"value")
                    properties[name]=value;
                    LOG.debug(" "*10+"Loading properties��"+name+":"+"value"+value)
            plugin_list.append(Plugin(plugin_name,plugin_file,properties,plugin_description))
        plugins_list.append(Plugins(plugins_name,plugins_dir,plugins_interval,plugin_list,plugins_disable))
    return Collectors(plugins_list)

def read_xml_leaf_value(element,tag_name):
    tag_element=element.getElementsByTagName(tag_name)[0]
    if tag_element!=None and len(tag_element.childNodes)>0:
        if tag_element.childNodes[0]!=None:
            return str(tag_element.childNodes[0].data)
    return ""





def init_env():
    reload(sys)
    sys.setdefaultencoding('utf-8')
    LOG.setLevel(logging.DEBUG)






def start_monitor():
    collectors=read_coTTig()
    collectors.start()
    # at this point we're ready to start processing, so start the ReaderThread
    # so we can have it running and pulling in data for us
    LOG.setLevel(logging.DEBUG)

if __name__ == '__main__':
    init_env()
