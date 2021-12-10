{{/*
Name of Chart.
*/}}
{{- define "kafka-signle.name" -}}
{{- .Chart.Name }}
{{- end -}}


{{/*
Name of Release. fully qualified app name.
*/}}
{{- define "kafka-signle.fullname" -}}
{{- .Release.Name }}
{{- end -}}

{{/*
Labels of Chart.
*/}}
{{- define "kafka-signle.labels" -}}
app: {{ .Release.Name }}
{{- end -}}