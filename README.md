Hindsight
=========

Hindsight is an application for streaming and controling yous WiFi enabled GoPro. Currently we have no backend system for importing your GoPro WiFi password. This means in order for the REC and STOP commands to work you must compile the application with this information.

## REC Activity

```
http://10.5.5.9/bacpac/SH?t=YOURPASSWORD&p=%01
```

At line 69 in RecordVideo.java you must replace YOURPASSWORD with your GoPro WiFi password.

## STOP Activity

```
http://10.5.5.9/bacpac/SH?t=YOURPASSWORD&p=%00
```

At line 71 in StopVideo.java you must replace YOURPASSWORD with your GoPro WiFi password.


## Outside Resources

The majority of the heavy lifting in this application is performed with Vitamio. If you intend to build the application yourself you will need to download the Vitamio Android SDK and use it as a library for your project. Form more information visit http://www.vitamio.org/en/.
