#!/bin/bash

#kubectl autoscale deployment svr3-deployment --min=1 --max=10 --cpu-percent=40

kubectl delete hpa/svr3-deployment -n ns-demo
