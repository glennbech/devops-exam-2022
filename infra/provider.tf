terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.33.0"
    }
  }
  backend "s3" {
    bucket = "analytics-1022"
    key    = "analytics-1022.state"
    region = "eu-west-1"
  }
}