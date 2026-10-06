job('ejemplo2-job-DSL'){
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm{
    git('https://github.com/DeveloperJulioT/job-jenkins-parametrizado.git', 'main') { node ->
      node / gitConfigName('DeveloperJulioT')
      node / gitConfigEmail('julio.david.1338@gmail.com')
    }
  }
  parameters {
    stringParam('nombre', defaultValue = 'Julio', description = 'Parametro de cadena para el Job parametrizado')
  	choiceParam('planeta',['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
  	booleanParam('agente', false)  
  }
  triggers {
  	cron('H/7 * * * *')
    githubPush()
  }
  steps{
    shell("bash jobscript.sh")  
  }
  publishers{
    mailer('julio.david.1338@gmail.com', true, true)
  }
}
