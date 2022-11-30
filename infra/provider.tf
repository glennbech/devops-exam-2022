terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.33.0"
    }
  }
  backend "aws_s3_bucket" {
    bucket = "analytics-state-files"
    key    = "1022-shop.state"
    region = "eu-west-1"
  }
}