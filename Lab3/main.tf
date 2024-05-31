provider "aws" {
  access_key                  = "test"
  secret_key                  = "test"
  region                      = "us-east-1"
  skip_credentials_validation = true
  skip_requesting_account_id  = true
  skip_region_validation      = true
  s3_use_path_style           = true


  endpoints {
    s3    = "http://localhost:4566"
    lambda = "http://localhost:4566"
    iam     = "http://localhost:4566"
    sqs   = "http://localhost:4566"
  }
}

 resource "aws_s3_bucket" "s3_start" {
   bucket = "s3-start"
 }

 resource "aws_s3_bucket" "s3_finish" {
   bucket = "s3-finish"

 }

 resource "aws_sqs_queue" "s3_event_queue" {
   name = "s3-event-queue"
 }

 resource "aws_iam_role" "lambda_exec_role" {
   name = "lambda_exec_role"

   assume_role_policy = jsonencode({
     Version = "2012-10-17",
     Statement = [
       {
         Action = "sts:AssumeRole",
         Effect = "Allow",
         Sid    = "",
         Principal = {
           Service = "lambda.amazonaws.com"
         }
       }
     ]
   })
 }

 resource "aws_iam_role_policy_attachment" "lambda_exec_role_attach" {
   role       = aws_iam_role.lambda_exec_role.name
   policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
 }

 resource "aws_lambda_function" "s3_copy_lambda" {
   function_name = "s3_copy_lambda"
   role          = aws_iam_role.lambda_exec_role.arn
   handler       = "index.handler"
   runtime       = "python3.8"
   timeout       = 60
   filename      = "lambda_function.zip"

   source_code_hash = filebase64sha256("lambda_function.zip")

   environment {
     variables = {
       SOURCE_BUCKET      = aws_s3_bucket.s3_start.bucket
       DESTINATION_BUCKET = aws_s3_bucket.s3_finish.bucket
     }
   }
 }

 resource "aws_lambda_permission" "allow_sqs" {
   statement_id  = "AllowSQSInvoke"
   action        = "lambda:InvokeFunction"
   function_name = aws_lambda_function.s3_copy_lambda.function_name
   principal     = "sqs.amazonaws.com"
   source_arn    = aws_sqs_queue.s3_event_queue.arn
 }

 resource "aws_s3_bucket_notification" "s3_start_notification" {
   bucket = aws_s3_bucket.s3_start.id

   queue {
     queue_arn = aws_sqs_queue.s3_event_queue.arn
     events    = ["s3:ObjectCreated:*"]
   }
 }

 resource "aws_sqs_queue_policy" "s3_event_queue_policy" {
   queue_url = aws_sqs_queue.s3_event_queue.id

   policy = jsonencode({
     Version = "2012-10-17",
     Statement = [
       {
         Effect = "Allow",
         Principal = "*",
         Action = "sqs:SendMessage",
         Resource = aws_sqs_queue.s3_event_queue.arn,
         Condition = {
           ArnEquals = {
             "aws:SourceArn": aws_s3_bucket.s3_start.arn
           }
         }
       }
     ]
   })
 }
