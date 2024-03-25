/** *************************************************************************************
 * Copyright (c) 2020-2021 Institute of Computing Technology, Chinese Academy of Sciences
 * Copyright (c) 2020-2021 Peng Cheng Laboratory
 *
 * XiangShan is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 * http://license.coscl.org.cn/MulanPSL2
 *
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 *
 * See the Mulan PSL v2 for more details.
 * *************************************************************************************
 */

package coupledL2.tl2chi

import chisel3._
import chisel3.util._
import utility._
import org.chipsalliance.cde.config.Parameters
import coupledL2.RespBundle

class RXRSP(implicit p: Parameters) extends TL2CHIL2Module {
  val io = IO(new Bundle() {
    val out = Flipped(ChannelIO(new CHIRSP()))
    val in = Output(new RespBundle())
  })

  /* Response to MSHR for CompData CompDBIDResp*/
  io.in.valid := io.out.valid 
  io.in.mshrId := io.out.bits.txnid
  io.in.set := 0.U(setBits.W)
  io.in.tag := 0.U(tagBits.W)
  io.in.respInfo.opcode := io.out.bits.opcode


  // TODO
  io <> DontCare

}
