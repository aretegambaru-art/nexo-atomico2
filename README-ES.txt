EL NEXO ATÓMICO · ESTRUCTURA CAPACITOR

Esta carpeta deja la app lista para envolverla como APK Android. La interfaz completa está en www/index.html y ya llama a AndroidBridge.scheduleExactAlarm(...).

PASOS EN UN EQUIPO CON Node.js, Android Studio y Java:
1. Abre una terminal dentro de esta carpeta.
2. Ejecuta: npm install
3. Ejecuta: npx cap add android
4. Copia el contenido de android-patch/app/src/main/java/com/arete/nexoatomico/ dentro de android/app/src/main/java/com/arete/nexoatomico/
5. Agrega los permisos y el receiver de android-patch/AndroidManifest-additions.xml en android/app/src/main/AndroidManifest.xml.
6. En MainActivity.java usa el MainActivity.java incluido para registrar AndroidBridge.
7. Ejecuta: npx cap sync android
8. Ejecuta: npx cap open android y compila desde Android Studio.

En Android 12 o superior hay que permitir alarmas exactas. En Android 13 o superior hay que aceptar las notificaciones. El receptor nativo despierta el teléfono y muestra sonido/vibración aunque la app esté cerrada.

Para Google, coloca tu Client ID web en la constante GOOGLE_CLIENT_ID dentro de www/index.html y agrega el dominio autorizado en Google Cloud.
