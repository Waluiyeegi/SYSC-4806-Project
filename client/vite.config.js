import { defineConfig } from 'vite'
import { svelte } from '@sveltejs/vite-plugin-svelte'

// https://vite.dev/config/
export default defineConfig({
  plugins: [svelte()],
  server: {
    port: 5173, // Svelte dev server
    proxy: {
      "/api": "http://localhost:8080" // Proxy to Spring Boot server
    }
  }
})
