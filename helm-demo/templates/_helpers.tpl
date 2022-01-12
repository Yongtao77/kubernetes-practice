{{/*
Name of Chart.
*/}}
{{- define "helm-demo.name" -}}
{{- .Chart.Name }}
{{- end -}}


{{/*
Name of Release. fully qualified app name.
*/}}
{{- define "helm-demo.fullname" -}}
{{- .Release.Name }}
{{- end -}}

{{/*
Labels of Chart.
*/}}
{{- define "helm-demo.labels" -}}
app: {{ .Release.Name }}
{{- end -}}