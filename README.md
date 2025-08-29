Запуск тестов
mvn clean test -Dbase_url="http://127.0.0.1:4723/wd/hub" -DdeviceName="emulator-5554" -DappPackage="com.pyankoff.andy" -DappActivity=".MainActivity" -DnoReset="false" -DappUrl="http://localhost:8888/Andy-257946-a51d3d.apk"
