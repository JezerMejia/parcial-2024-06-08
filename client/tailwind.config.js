/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    extend: {
      boxShadow: {
        'header': "0px 2px 12px 0px rgba(0,52,116,0.15);"
      },
      colors: {
          yellow: {
            100: "#FFFFF0",
            200: "#FFFFD7",
            300: "#FFFF9A",
            400: "#B6B65D",
          },
          green: {
            100: "#F0FFF7",
            200: "#D7FFEB",
            300: "#9AFFCD",
            400: "#00743A",
          },
          red: {
            100: "#FFF0F0",
            200: "#FFD7D7",
            300: "#FF9A9A",
            400: "#740000",
          },
          blue: {
            100: "#f0f6ff",
            200: "#d7e9ff",
            300: "#9ac9ff",
            400: "#003474",
            500: "#001024",
          },
          shades: {
            100: "#F6F8FA",
            200: "#E6E6E6",
            300: "#B5B5B5",
            400: "#747474",
          },
        },
    },
    fontFamily: {
      poppins: "Poppins, sans-serif",
    },
  },
  plugins: [],
};
