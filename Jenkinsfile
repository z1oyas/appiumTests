import groovy.json.JsonSlurperClassic
import java.util.Date
import java.text.SimpleDateFormat


def slurped = [:]

timeout(1200){
    node("maven") {
        try {
            sh "mkdir -p envs"

            def yamlConfig = readYaml text: params.YAML_CONFIG

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
                sh "rm -rf allure-results || true"
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

            stage("Gets statistics from allure artifacts") {
                def jsonLines = readFile "allure-report/widgets/summary.json"
                slurped = new JsonSlurperClassic().parseText(jsonLines)

                sh "echo $slurped"
            }

            stage("Telegram notification") {
            def dateUnixStart = slurped.time.start as long
            def dateUnixStop = slurped.time.stop as long


            def duration = ((slurped.time.duration as long) / 1000) as long
            Date dateObjStart = new Date(dateUnixStart)
            Date dateObjStop = new Date(dateUnixStop)

            def cleanDateStart = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss').format(dateObjStart)
            def cleanDateStop = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss').format(dateObjStop)
            //
            def durationMin = (duration /60) as long
            def durationSec = (duration %60) as long
            def messageContent = """====MOBILE TESTS RESULT =====

                Test Results:
                Passed: ${slurped.statistic.passed}
                Failed: ${slurped.statistic.failed}
                Broken: ${slurped.statistic.broken}
                Skipped: ${slurped.statistic.skipped}
                Total: ${slurped.statistic.total}

                Start: $cleanDateStart
                Finish: $cleanDateStop
                Duration: $durationMin min $durationSec sec"""



                withCredentials([string(credentialsId: 'chat_id', variable: 'chatId'), string(credentialsId: 'bot_token',variable: 'botToken')]){
                    sh """
                    curl -X POST \
                    -H 'Content-Type: application/json' \
                    -d '{"chat_id": "${chatId}", "text": "${messageContent}"}' \
                    "https://api.telegram.org/bot${botToken}/sendMessage"
                    """
                }
            }
        }
        finally {
            stage("Cleanup") {
                sh "docker rm -f mobile_tests"
            }
        }
    }
}