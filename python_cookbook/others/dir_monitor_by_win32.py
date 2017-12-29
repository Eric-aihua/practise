# encoding:utf-8
__author__ = 'sunaihua'

import os
import win32file

import win32con

"""监控src目录，如果发现src下的内容有改动，自动拷贝到目标文件夹.适合用在windows，如果是Linux可以使用pyinotify"""

DST_KEY = 'f:\\Tools\\key\\241.ppk'

LOCAL_SRC_DIR = 'H:\\sourcecode\\Rai\\trunk\\api'
DST_DIR = '/opt/disk2/var/www/rai'
DST_HOSTS = ['10.5.24.82']

# LOCAL_SRC_DIR = 'H:\\sourcecode\\Rai\\trunk\\rai_report'
# DST_HOSTS = ['10.5.31.33']
# DST_DIR = '/opt/disk2/var/www/bailu/rai_report'

# LOCAL_SRC_DIR = 'H:\\sourcecode\\Talas\\branches\\3.0\\dev'
# DST_HOSTS = ['10.5.24.137','10.5.24.138','10.5.24.139','10.5.24.140']
# DST_DIR = '/opt/PaaS/Talas/dev'

# LOCAL_SRC_DIR = 'H:\\sourcecode\\Talas\\talas_plugins\\rai\\pluginb'
# DST_HOSTS = ['10.5.24.5','10.5.24.6','10.5.24.8','10.5.24.9']
# DST_DIR = '/opt/PaaS/Talas/var/streaming/pluginb/rai'



file_md5_map = {}
syn_list = []


ACTIONS = {
    1: "Created",
    2: "Deleted",
    3: "Updated",
    4: "Renamed from something",
    5: "Renamed to something"
}

FILE_LIST_DIRECTORY = win32con.GENERIC_READ | win32con.GENERIC_WRITE

hDir = win32file.CreateFile(
    LOCAL_SRC_DIR,
    FILE_LIST_DIRECTORY,
    win32con.FILE_SHARE_READ | win32con.FILE_SHARE_WRITE,
    None,
    win32con.OPEN_EXISTING,
    win32con.FILE_FLAG_BACKUP_SEMANTICS,
    None
)

if __name__ == '__main__':
    while 1:
        has_change = False
        results = win32file.ReadDirectoryChangesW(
            hDir,
            # handle: Handle to the directory to be monitored. This directory must be opened with the FILE_LIST_DIRECTORY access right.
            1024,  # size: Size of the buffer to allocate for the results.
            True,
            # bWatchSubtree: Specifies whether the ReadDirectoryChangesW function will monitor the directory or the directory tree.
            win32con.FILE_NOTIFY_CHANGE_FILE_NAME |
            win32con.FILE_NOTIFY_CHANGE_DIR_NAME,
            None,
            None)
        # print results
        for action, file in results:
            full_filename = os.path.join(LOCAL_SRC_DIR, file)
            if full_filename.endswith('.py') or full_filename.endswith('phtml') or full_filename.endswith('json'):
                print 'file has change: %s' % full_filename
                abs_path = full_filename.replace('\\', os.path.sep)
                native_path= abs_path[len(LOCAL_SRC_DIR):]
                has_change = True
                str_native_path=native_path.replace(os.path.sep,'/')
                dst_file_path= '%s%s'%(DST_DIR,str_native_path)
                for host in DST_HOSTS:
                    cmd = 'pscp -i %s %s root@%s:%s' % (DST_KEY, abs_path, host, dst_file_path)
                    print cmd
                    os.system(cmd)
