#!/bin/bash
for siteip in $(seq 200 206)
do
	site="192.168.100.${siteip}"
	ping -c1 -W2 ${site} &> /dev/null
	if [ "$?" == "0" ]; then
		echo "$site is UP"
	else
		echo "$site is DOWN"
	fi
done
