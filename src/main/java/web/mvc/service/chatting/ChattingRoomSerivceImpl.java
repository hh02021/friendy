package web.mvc.service.chatting;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.mvc.dto.chat.ChattingRoomDTO;
import web.mvc.entity.chatting.ChattingRoom;
import web.mvc.entity.chatting.MessageLog;
import web.mvc.entity.user.Users;
import web.mvc.repository.chatting.ChattingRoomDetailImgRepository;
import web.mvc.repository.chatting.ChattingRoomRepository;
import web.mvc.repository.chatting.MessageLogRepository;
import web.mvc.repository.user.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class ChattingRoomSerivceImpl implements ChattingRoomService {

   private final ChattingRoomRepository chattingRoomRepository;
   private final ChattingRoomDetailImgRepository chattingRoomDetailImgRepository;
   private final MessageLogRepository messageLogRepository;
    private final UserRepository userRepository;


    @Override
    public ChattingRoom createChattingRoom(ChattingRoomDTO chattingRoomDTO) {
        Users users = userRepository.findUserByUserId(chattingRoomDTO.getUserId());
        String userNickname = users.getNickName();
        LocalDate today = LocalDate.now();
        int randomNumber = new Random().nextInt(100000);
        String roomId = userNickname + today.getMonthValue() + today.getDayOfMonth() + randomNumber;
        ChattingRoom chattingRoom = ChattingRoom.builder()
                .chattingRoomImgSeq(chattingRoomDTO.getChattingRoomImgSeq())
                .roomId(roomId)
                .build();
        chattingRoomRepository.save(chattingRoom);

        return null;
    }

    @Override
    public String deportChat(Long userId) {
        return null;
    }

    @Override
    public String deleteChatRoom(ChattingRoom chattingRoom) {
        return null;
    }

    @Override
    public List<MessageLog> findMessageLog(String roomId) {

        List<MessageLog> list= messageLogRepository.findMessageLogByRoomId(roomId);
        return list;
    }
}
