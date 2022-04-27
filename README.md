# Проект по автоматизации тестов для магазина citilink
<a target="_blank" href="https://www.citilink.ru/">Citilink</a>

<p align="center">
<img title="Allure Graphics" src="images/screens/citilink.png">
</p>


## Содержание
+ [Описание проекта](#Description)
+ [Технологии и инструменты](#Technology)
+ [Запуск тестов в Jenkins](#Jenkins)
    + [Параметры  сборки](#SystemProperty)
+ [Отчет о результатах тестирования в Allure Report](#AllureReport)
+ [Интеграция с Allure TestOps](#AllureTestOps)
+ [Результаты выполнения тестов](#Results)



## <a name="Description">Описание проекта</a>
- Данный проект реализован как финальная работа по подведению итогов прохождения курсов на портале QA GURU;
- В качестве объекта тестирования выбран портал citilink.ru;




# <a name="Technology">Технологии и инструменты</a>
<p  align="center">
  <code><img width="5%" title="IntelliJ IDEA" src="images/logo/IDEA-logo.svg"></code>
  <code><img width="5%" title="Java" src="images/logo/java-logo.svg"></code>
  <code><img width="5%" title="Selenide" src="images/logo/selenide-logo.svg"></code>
  <code><img width="5%" title="REST-Assured" src="images/logo/rest-assured-logo.svg"></code>
  <code><img width="5%" title="Selenoid" src="images/logo/selenoid-logo.svg"></code>
  <code><img width="5%" title="Gradle" src="images/logo/gradle-logo.svg "></code>
  <code><img width="5%" title="JUnit5" src="images/logo/junit5-logo.svg"></code>
  <code><img width="5%" title="Allure Report" src="images/logo/allure-Report-logo.svg"></code>
  <code><img width="5%" title="Allure TestOps" src="images/logo/allure-ee-logo.svg"></code>
  <code><img width="5%" title="Github" src="images/logo/GitHub.svg"></code>
  <code><img width="5%" title="Jenkins" src="images/logo/jenkins-logo.svg"></code>
  <code><img width="5%" title="Telegram" src="images/logo/Telegram.svg"></code>
  <code><img width="5%" title="Browserstack" src="images/logo/browserstack.svg"></code>
  <code><img width="5%" title="Android Studio" src="https://upload.wikimedia.org/wikipedia/commons/9/95/Android_Studio_Icon_3.6.svg"></code>
  <code><img width="5%" title="Appium" src="images/logo/appium.svg"></code>

</p>

Проект реализован на **Java** с использованием фреймворка **Selenide**.
Для сборки проекта используется **Gradle**.  
**JUnit 5** используется как фреймворк для модульного тестирования.
Запуск тестов выполняется из **Jenkins**.
**Selenoid** используется для запуска браузеров в контейнерах **Docker**.
**Browserstack** используется для запуска мобильных тестов, также для локального запуска
используются инструменты **Android Studio** и **Appium**.
**Allure Report, Telegram Bot** используются для визуализации результатов тестирования.



# <a name="Jenkins">Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/10-DmitryOshkin-hw-25-diploma/)</a>

Для запуска тестов на разных инструментах в Jenkins была создана job (джоба). 
Для возможности запуска разных видов тестов (API, UI, Mobile), добавлены параметры:
**TASK** - для выбора задачи по запуску тестов api_tests, ui_tests, mobile_android_tests.
**DEVICEHOST** - для выбора устройства на котором будут запущены тесты (ui - удаленный запуск ui и api тестов на Selenoid,
browserstack - для запуска mobile тестов на ресурсе browserstack).
**Не обязательные параметры**:
**COMMENT** - комментарий для нотификации по результату прогона.
**BOTTOKEN** - токен настроенного Telegram bota который отправляет оповещения о результатах прогона.
**CHATID** - id Telegram чата в котором происходит информирование о результатах прогона.
**ENVIRONMENT** - для выбора окружения на котором запускаются сценарии. 


Для запуска небходимо заполнить параметры сборки показанные на скришоте ниже.
<p  align="center">
<img src="images/screens/run_test.png" alt="Allure Report" width="850">
</p>


## <a name="SystemProperty">Параметры  сборки</a>
### Локальный запуск :

```
gradle clean ${TASK} -DdeviceHost=${DEVICEHOST}
```

### Удаленный запуск :
```
clean 
${TASK} 
-DdeviceHost=${DEVICEHOST}
```


# <a name="AllureReport">Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/10-DmitryOshkin-hw-25-diploma/14/allure/)</a>

#### Общая информация
Главная страница Allure-отчета содержит следующие информационные блоки:

>- <code><strong>*ALLURE REPORT*</strong></code> - отображает дату и время прохождения теста, общее количество прогнанных кейсов, а также диаграмму с указанием процента и количества успешных, упавших и сломавшихся в процессе выполнения тестов
>- <code><strong>*TREND*</strong></code> - отображает тренд прохождения тестов от сборки к сборке
>- <code><strong>*SUITES*</strong></code> - отображает распределение результатов тестов по тестовым наборам
>- <code><strong>*CATEGORIES*</strong></code> - отображает распределение неуспешно прошедших тестов по видам дефектов
<p align="center">
  <img src="images/screens/Allure Report.png" alt="Allure Report" width="650">
</p>

### Список тестов c описанием шагов и визуализацией результатов
На данной странице представляется стандартное распределение выполнявшихся тестов по тестовым наборам или классам, в
которых находятся тестовые методы.

<p align="center">
  <img src="images/screens/Allure Report steps.png" alt="Allure Report" width="650">
</p>

### Графики
На данной странице представляется стандартное распределение выполнявшихся тестов по тестовым наборам или классам, в
которых находятся тестовые методы.

<p align="center">
  <img src="images/screens/Allure_Report2.png" alt="Allure Report" width="650">
</p>

# <a name="AllureTestOps">Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/1260/)</a>

### Основной дашборд
<p align="center">
  <img src="images/screens/allureTestOPS dashboards.png" alt="dashboards" width="650">
</p>

### Дашборд по разным типам тестов
<p align="center">
  <img src="images/screens/allureTestOPS dashboards test types.png" alt="dashboards test types" width="650">
</p>

### Запуски
<p align="center">
  <img src="images/screens/allureTestOPS launches.png" alt="launches" width="650">
</p>

### Результат запуска
<p align="center">
  <img src="images/screens/allureTestOPS launch.png" alt="launch" width="750">
</p>

### Тест-кейсы
<p align="center">
  <img src="images/screens/Test cases.png" alt="test cases" width="750">
</p>







# <a name="Results">Результаты выполнения тестов</a>

### Пример запуска теста в Browserstack
<p align="center">
  <img src="images/gif/videoMob.gif" alt="video" width="700">
</p>

### Пример запуска теста в Selenoid
<p align="center">
    <img src="images/videoUI.gif" alt="defects" width="900">
</p>

### Уведомления в Telegram
<p align="center">
  <img src="images/screens/tlgrm.png" alt="Telegram" width="550">
</p>
