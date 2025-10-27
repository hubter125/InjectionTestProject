A lightweight Java project that simulates a log ingestion and detection pipeline for identifying SQL injection and XSS attacks in log files.  

Score Currently does not correlate to anything, planning to map it to Risk Matrix eventually  

InjectionTestProject/  
│  
├── src/  
│   └── main/  
│       └── java/  
│           └── detector/  
│               ├── Ingestor.java          # Reads file line by line  
│               ├── Detector.java          # Base detection interface  
│               ├── SqlDetector.java       # Detects SQL injection patterns  
│               └── XssDetector.java       # Detects XSS patterns  
│
├── example.txt                            # Example log file  
└── README.md  