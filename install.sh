#!/bin/bash

echo Installing browser docker images
docker pull selenoid/vnc:chrome_61.0
docker pull selenoid/vnc:firefox_57.0
docker pull selenoid/video-recorder:latest-release

