export NS="sitting-ducks2"
kubectl create namespace $NS
kubectl config set-context --current --namespace=$NS
kubectl config view --minify --output 'jsonpath={..namespace}'

kubectl apply -f sitting-ducks.k8s.yaml

# oc new-app --name=sitting-ducks caravanacloud/sitting-ducks:1.0.0
# oc describe deployment sitting-ducks
# oc get pods -l app=sitting-ducks
# kubectl logs replicaset.apps/sitting-ducks-