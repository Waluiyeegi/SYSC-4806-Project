import { defineConfig } from 'vite'
import { svelte } from '@sveltejs/vite-plugin-svelte'

// https://vite.dev/config/
export default defineConfig({
  plugins: [svelte()],
  server: {
    port: 5173, // Svelte dev server
    proxy: {
      "/api": {
        target: "http://localhost:8080", // Backend server URL
        changeOrigin: true,            // Handle CORS issues
        secure: false,                 // Allow insecure (self-signed) certificates
      },
    },
  }
})
