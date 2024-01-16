kubectl create namespace sitting-ducks
kubectl config set-context --current --namespace=sitting-ducks
kubectl config view --minify --output 'jsonpath={..namespace}'