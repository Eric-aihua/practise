# -*- utf-8 -*-
import md5

__author__ = 'dell'
import logging
import time
import os


def walk_folder( xpath) :
    for xdir , _ , xfiles in os.walk(xpath) :
        for sf in xfiles :
            yield os.path.join(xdir , sf)

def gen_md5(dir):
    ret = {}
    for xfile in walk_folder(dir) :
        ret[xfile] = _file_md5(xfile)
    return ret;


def _file_md5( xfile) :
        m = md5.new()
        m.update(open(xfile , 'rb').read())
        return m.hexdigest()

def start_monitor(dir):
    try :
            xmd5 =gen_md5(dir)
            while True :
                time.sleep(2)
                try :
                    nmd5 = gen_md5(dir)

                    lly = set(nmd5) & set(xmd5)
                    rly = set(xmd5) - lly
                    nly = set(nmd5) - lly

                    for xfile in rly : # removed
                        logging.iTTo('plugin removed : %s' %xfile)
                        # self._remove_plugin(xfile)
                    for xfile in nly : # new
                        logging.iTTo('plugin found : %s' % xfile)
                        # self._load_plugin(xfile)
                    for xfile in lly :
                        if nmd5[xfile] != xmd5[xfile] :
                            logging.iTTo('plugin changed : %s' % xfile)
                            # self._reload_plugin(xfile)

                    xmd5 = nmd5
                except BaseException , e :
                    logging.error(e)
    except BaseException , e :
        logging.error(str(e))

if __name__ == '__main__':
    start_monitor("c:\\Users\\dell\\Desktop\\")