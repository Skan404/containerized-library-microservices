FROM nginx:latest

COPY dist/lab5/browser/ /usr/share/nginx/html


COPY nginx.conf /etc/nginx/conf.d/default.conf



EXPOSE 80
