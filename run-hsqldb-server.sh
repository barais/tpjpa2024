mvn dependency:copy-dependencies
mkdir data 2> /dev/null
cd data
java -cp ../target/dependency/hsqldb-2.3.4.jar org.hsqldb.Server
