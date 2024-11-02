import os

os.system('javac -d build *.java dependecies/*.java ')
os.system('appletviewer build/index.html')