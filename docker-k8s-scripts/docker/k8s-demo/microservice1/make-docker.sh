#!/bin/bash
# echo ----$1----

#source /etc/profile
#source ~/.bash_profile

if [ ! -n "$1" ]; then
  echo '---- usages ----'
  echo 'sh make-docker.sh {version}'
  echo 'example:'
  echo '  sh make-docker.sh 1.0.0'
  exit 2
fi

jarfilename=`ls |grep .jar`
echo ${jarfilename##*/}
if [[ $jarfilename != *$1.jar ]]; then
  echo "----jarfile:[$jarfilename] and input version:[$1] mismatch----"
  exit 2
fi

echo '----prepare & build docker image----'
rm -rf Dockerfile 
cp template.docker Dockerfile
sed -i "s/{version}/$1/g" Dockerfile

#docker build -t="demo-svc1:$1" ./
#docker tag demo-svc1:$1 harbor.sanss.com/demo/demo-svc1:$1

#docker rmi harbor.sanss.com/demo/demo-svc1:$1

docker build --tag  harbor.sanss.com/demo/demo-svc1:$1 ./


echo '----upload image to harbor.sanss.com----'
docker push harbor.sanss.com/demo/demo-svc1:$1

rm -f Dockerfile
rm -f *.jar
