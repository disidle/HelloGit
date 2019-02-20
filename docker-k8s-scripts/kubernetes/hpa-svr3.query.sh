#!/bin/bash

echo ''
echo ''
echo '----kubectl get hpa -n ns-demo----'
kubectl get hpa -n ns-demo

echo ''
echo ''
echo '----kubectl get pod -l name=pod-svr3 -n ns-demo|wc -l----'
kubectl get pod -l name=pod-svr3 -n ns-demo|wc -l


echo ''
echo ''
echo '----kubectl top pod -l name=pod-svr3 -n ns-demo----'
kubectl top pod -l name=pod-svr3 -n ns-demo


echo ''
echo ''
