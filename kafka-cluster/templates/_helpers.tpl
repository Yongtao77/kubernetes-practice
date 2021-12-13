{{/*
Name of Chart.
*/}}
{{- define "kafka-cluster.name" -}}
{{- .Chart.Name }}
{{- end -}}


{{/*
Name of Release. fully qualified app name.
*/}}
{{- define "kafka-cluster.fullname" -}}
{{- .Release.Name }}
{{- end -}}

{{/*
Labels of Chart.
*/}}
{{- define "kafka-cluster.labels" -}}
app: {{ .Release.Name }}
{{- end -}}