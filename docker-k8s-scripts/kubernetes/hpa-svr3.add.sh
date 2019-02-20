#!/bin/bash

kubectl autoscale deployment svr3-deployment --min=1 --max=10 --cpu-percent=80 -n ns-demo
