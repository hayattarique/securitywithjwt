pipeline{
stages{
stage('Cloning Repository'){
steps{
echo 'cloning repository'
git branch: 'main', url: 'https://github.com/hayattarique/securitywithjwt.git'
    }
  }
  
  stage('Executing Test'){
  echo 'test Execution started'
  bat 'mvn clean test'
  }
 stage('packing ') {
 echo 'generating artifacts'
 bat 'mvn package'
 }
 }
 
}
