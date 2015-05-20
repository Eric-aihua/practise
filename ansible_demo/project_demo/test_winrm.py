import winrm

s = winrm.Session('https://192.168.40.121:5986/wsman',auth=('aihua.sun','sunaihua@126'))
r = s.run_cmd('ipconfig')
print r
