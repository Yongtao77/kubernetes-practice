{{/*
Name of Chart.
*/}}
{{- define "mysql.name" -}}
{{- .Chart.Name }}
{{- end -}}


{{/*
Name of Release. fully qualified app name.
*/}}
{{- define "mysql.fullname" -}}
{{- .Release.Name }}
{{- end -}}

{{/*
Labels of Chart.
*/}}
{{- define "mysql.labels" -}}
app: {{ .Release.Name }}
{{- end -}}