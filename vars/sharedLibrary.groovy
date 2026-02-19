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
