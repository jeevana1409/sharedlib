def gitDownload(String repo) {
    if (!repo) {
        error "Repository name must be provided!"
    }

    echo "Cloning repository: ${repo} (branch: main)"
    
    git(
        url: "https://github.com/jeevana1409/${repo}.git",
        branch: 'main',
        credentialsId: '' // add GitHub credentials ID if repo is private
    )
}
def build()
{ 
  sh 'mvn package'
}
def prepareDockerContext() 
{
    sh '''
        mkdir -p docker
        cp webapp/target/webapp.war docker/webapp.war
        cat <<EOF > docker/Dockerfile
FROM tomcat:10
COPY webapp.war /usr/local/tomcat/webapps/
EXPOSE 8080
EOF
    '''
}
def buildDockerImage(imageName) 
{
    sh "docker build -t ${imageName} docker"
}
