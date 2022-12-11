resource "aws_cloudwatch_metric_alarm" "zerosum" {
  alarm_name                = "shpping-cart-alarm-greater-than-5!"
  namespace                 = "1022"
  metric_name               = "carts_count.value"

  comparison_operator       = "GreaterThanThreshold"
  threshold                 = "5"
  evaluation_periods        = "3"
  period                    = "15"

  statistic                 = "Maximum"

  alarm_description         = "goes off when cart over 5 in 15 min "
  insufficient_data_actions = []
  alarm_actions       = [aws_sns_topic.alarms.arn]
}

resource "aws_sns_topic" "alarms" {
  name = "alarm-topic-${var.candidate_id}"
}

resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
  topic_arn = aws_sns_topic.alarms.arn
  protocol  = "email"
  endpoint  = var.candidate_email
}