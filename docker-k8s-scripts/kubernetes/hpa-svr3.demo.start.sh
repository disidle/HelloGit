#!/bin/bash
# echo ----$1----
echo 'aaa'
for i in $(seq 1 50)  
do
  echo "----$i--1----"
  curl http://10.68.224.201:18003/callhistroy?phoneno=189189xxxx &
  sleep 10
  echo "----$i--2----"
  curl http://10.68.224.201:18003/callhistroy?phoneno=189189xxxx &
  sleep 10
  #echo "----$i--3----"
  #curl http://10.68.224.201:18003/callhistroy?phoneno=189189xxxx &
  #sleep 0.2
  #echo "----$i--4----"
  #curl http://10.68.224.201:18003/callhistroy?phoneno=189189xxxx &
  #sleep 0.2
  #echo "----$i--5----"
  #curl http://10.68.224.201:18003/callhistroy?phoneno=189189xxxx &
  #sleep 0.2
  #sleep 10
done

sleep 600


