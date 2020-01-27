FROM gitpod/workspace-full:latest

# Install Maven bash completion
# Download Karaf and the agent
USER gitpod
# Set path to include Karaf that will be downloaded during the init task
ENV PATH="/workspace/apache-karaf-4.2.8/bin/:$PATH"
# return control
USER root