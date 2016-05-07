# AlexaApp
Alexa app to place order 

brew update
brew install maven

Add the below line to your ~/.bashrc
export PATH=$PATH:/usr/local/Cellar/maven/3.3.9/bin

Command to build the jar,
mvn assembly:assembly -DdescriptorId=jar-with-dependencies package
