import groovy.json.JsonSlurperClassic

def testsStatistics = [:]

timeout(1200){
    node("maven") {
        try {
            sh "mkdir -p envs"

            def yamlConfig = readYaml text: params.YAML_CONFIG
            def chatId = params.chat_id
            def botToken = params.bot_token

            stage("Create environment variables") {
                dir("envs") {
                   sh "echo 'DEVICE_NAME=${yamlConfig['deviceName']}' > .env"
                   sh "echo 'BASE_URL=${yamlConfig['base_url']}' >> .env"
                   sh "echo 'APP_PACKAGE=${yamlConfig['appPackage']}' >> .env"
                   sh "echo 'APP_ACTIVITY=${yamlConfig['appActivity']}' >> .env"
                   sh "echo 'NO_RESET=${yamlConfig['noReset']}' >> .env"
                   sh "echo 'APP_URL=${yamlConfig['appUrl']}' >> .env"
                }
            }
            stage("Prepare Allure results") {
                sh "mkdir -p allure-results"
            }
            stage("Running mobile Automation") {
                def status = sh(
                        script: "docker run --rm --name=mobile_tests --env-file envs/.env --network=host -v \$PWD/allure-results:/app/allure-results localhost:5005/mobile_tests:latest",
                        returnStatus: true
                )
                sh "ls -la \$PWD/allure-results"
                if (status > 0) {
                    currentBuild.result = 'UNSTABLE'
                }
            }

            stage("Debug allure mount") {
                sh "ls -la /home/jenkins/workspace/ui-test-runner/allure-results || true"
                sh "ls -la allure-results || true"
            }

            stage("Allure report publisher") {
                allure([
                        includeProperties: false,
                        jdk              : '',
                        properties       : [],
                        reportBuildPolicy: 'ALWAYS',
                        results          : [[path: 'allure-results']]
                ])
            }

//             stage("Gets statistics from allure artifacts") {
//                 def jsonLines = readFile "allure-report/widgets/summary.json"
//                 def slurped = new JsonSlurperClassic().parseText(jsonLines)
//
//                 slurped.each{k, v ->
//                     testsStatistics[k] =v
//                 }
//
//             }


//             stage("Telegram notification") {
//             def BROWSER = yamlConfig['browser']
//             def REMOTE = yamlConfig['remote']
//             def BROWSER_VERSION = yamlConfig['browser.version']
//                 def message = """=============UI TESTS RESULT ================
//                 browser name: $BROWSER
//                 remote: $REMOTE
//                 browser version: $BROWSER_VERSION
//                 """
//
//                 testsStatistics.each{k,v ->
//                     message += "\t\t$k: $v\n"
//                 }
//                 withCredentials([string(credentialsId: 'chat_id', variable: 'chatId'), string(credentialsId: 'bot_token',variable: 'botToken')]){
//                     sh """
//                     curl -X POST \
//                     -H 'Content-Type: application/json' \
//                     -d '{"chat_id": "$chatId", "text": "$message"}' \
//                     "https://api.telegram.org/bot$botToken/sendMessage"
//                     """
//                 }
//             }
        }
        finally {
            stage("Cleanup") {
                sh "docker rm -f mobile_tests"
            }
        }
    }
}