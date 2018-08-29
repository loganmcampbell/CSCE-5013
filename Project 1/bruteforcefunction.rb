def bruteforcefunction (ciphertext, word)
  testkey = 0

  until testkey == 25
      decipherword = decipherfunction(ciphertext, testkey)
      decipherword = decipherword.join()
      word = word.upcase
        if (word == decipherword)
          # p decipherword
          # p testkey
          return decipherword, testkey

          break
      end
      testkey = testkey + 1
  end
end
