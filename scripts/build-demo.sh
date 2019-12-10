# Execute this script in project root
cd ../web-monitor-demo &&
rm -rf build &&
npm run build &&
cd ../web-monitor &&
cp ../web-monitor-demo/build/* pro-main/src/main/resources/static/build/ &&
rm -rf ../web-monitor-demo/build

# Open http://localhost:9101/build/demo.html in Browser.
