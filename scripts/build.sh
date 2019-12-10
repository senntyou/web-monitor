# Execute this script in project root
cd ../web-monitor-admin &&
rm -rf build &&
npm run build &&
cd ../web-monitor &&
rm -rf pro-main/src/main/resources/templates &&
rm -rf pro-main/src/main/resources/static &&
mkdir pro-main/src/main/resources/templates &&
mkdir pro-main/src/main/resources/static &&
cp ../web-monitor-admin/build/admin.html pro-main/src/main/resources/templates &&
cp -R ../web-monitor-admin/build pro-main/src/main/resources/static &&
rm -rf ../web-monitor-admin/build &&
./gradlew pro-main:build
