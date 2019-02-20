#!/bin/bash
##create by huang jin jing (sanss)

####stop####

echo '----stop hpa demo----'
PROC="curl http"
PROCNUM=`ps -ef | grep "$PROC" | grep -v grep | awk '{print $2}'`

if [ ! -n "$PROCNUM" ]; then
  exit 0
else
  kill -9 $PROCNUM
fi

#kill -9 $PROCNUM


