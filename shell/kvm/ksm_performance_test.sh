#!/bin/bash
#file : ksm-test.sh
echo "-----------Stop ksm service"
service ksm stop
service ksmtuned stop
echo "-----------\"free -m \" Result:"
free -m
echo "start 9 centos guest"
for i in $(seq 1 9)
do
        qemu-img create -f qcow2 -o backing_file="/root/cirros-0.3.0-x86_64-disk.img" cirros-${i}.qcow2
        echo "Starting No ${i} Guest machine"
        qemu-kvm -m 1024 -smp 2 -net nic -daemonize /root/cirros-${i}.qcow2
        sleep 5
done

echo "-----------\"free -m \" Result:"
free -m

echo "-----------Start ksm service"
service ksm start
service ksmtuned start

sleep 60
echo "-----------\"free -m \" Result:"
free -m