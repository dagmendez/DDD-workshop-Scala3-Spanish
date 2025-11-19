package domain
import domain.ID

trait API:
  def validateID(input: String): ID | String
end API
