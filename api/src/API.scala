package api

trait API:
  def validateID(input: String): ID | String
end API
