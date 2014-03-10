Hindsight
=========

Hindsight is an application for streaming and controling yous WiFi enabled GoPro. Currently we have no backend system for importing your GoPro WiFi password. This means in order for the REC and STOP commands to work you must compile the application with this information.

## REC activity

```
http://10.5.5.9/bacpac/SH?t=YOURPASSWORD&p=%01
```

At line 69 in RecordVideo.java you must replace YOURPASSWORD with your GoPro WiFi password.


```
http://10.5.5.9/bacpac/SH?t=YOURPASSWORD&p=%00
```

At line 71 in StopVideo.java you must replace YOURPASSWORD with your GoPro WiFi password.
